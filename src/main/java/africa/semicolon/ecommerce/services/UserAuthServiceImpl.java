package africa.semicolon.ecommerce.services;

import africa.semicolon.ecommerce.data.model.Cart;
import africa.semicolon.ecommerce.data.model.ConfirmationToken;
import africa.semicolon.ecommerce.data.model.Role;
import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.data.repositories.UserRepository;
import africa.semicolon.ecommerce.dto.UserDto;
import africa.semicolon.ecommerce.dto.requests.SignUpRequest;
import africa.semicolon.ecommerce.dto.responses.SignUpResponse;
import africa.semicolon.ecommerce.email.MessageRequest;
import africa.semicolon.ecommerce.events.SendMessageEvent;
import africa.semicolon.ecommerce.util.GenerateToken;
import africa.semicolon.ecommerce.exceptions.TokenException;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService, UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws UserAlreadyExistException {
        validateEmail(signUpRequest.getEmail());

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(signUpRequest.getDateOfBirth(), dateTimeFormatter);
        User user = mapper.map(signUpRequest, User.class);
        user.setDateOfBirth(dateOfBirth);

        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setCart(new Cart());
        User registeredUser = userRepository.save(user);

        log.info("new user was created " + registeredUser.getEmail());

        String generatedToken = GenerateToken.generateToken();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(generatedToken);
        confirmationToken.setUser(registeredUser);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        tokenService.saveConfirmationToken(confirmationToken);

        log.info("token generate and saved");


        MessageRequest messageRequest = new MessageRequest();
        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + generatedToken;
        messageRequest.setSender("akinsolakolawole@gmail.com");
        messageRequest.setFirstName(registeredUser.getFirstName());
        messageRequest.setReceiver(registeredUser.getEmail());
        messageRequest.setBody("Kindly verify your account. " + link);
        messageRequest.setSubject("Confirmation Token");
        SendMessageEvent event = new SendMessageEvent(messageRequest);

        applicationEventPublisher.publishEvent(event);

        log.info("email sent to: " + messageRequest.getReceiver());

        return new SignUpResponse("sign up successfully, a verification link was sent to your" +
                " mail. Kindly verify your mail.", UserDto.pack(registeredUser));
    }

    private void validateEmail(String email) throws UserAlreadyExistException {
        Optional<User> user = userRepository.findUserByEmailIgnoreCase(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistException("Email already exist");
        }
    }

    @Override
    @Transactional
    public String confirmToken(String token) throws TokenException {

        ConfirmationToken confirmationToken = tokenService
                .getToken(token);

        log.info("this is the token : " + confirmationToken.getToken());


        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenService.saveConfirmationToken(confirmationToken);
        User user = confirmationToken.getUser();
        user.setIsVerified(Boolean.TRUE);

        userRepository.save(user);


        return "confirmed";
    }

    @Override
    public User getUserByEmail(String email) throws UserDoesNotExistException {
        return findUserByEmail(email);
    }

    private User findUserByEmail(String email) throws UserDoesNotExistException {
        Optional<User> user = userRepository.findUserByEmailIgnoreCase(email);
        if (user.isEmpty()) {
            throw new UserDoesNotExistException("User does not exist");
        }
        return user.get();

    }

    public ConfirmationToken findById(Long id) throws TokenException {
        return tokenService.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findUserByEmailIgnoreCase(email).orElseThrow(() -> new UserDoesNotExistException("user not found"));
        } catch (UserDoesNotExistException e) {
            log.error("user does not exist " + e.getMessage());
            //throw new RuntimeException(e);
        }
        org.springframework.security.core.userdetails.User returnedUser = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
        log.info("Returned user --> {}", returnedUser);
        return returnedUser;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getRoleType().name())
        ).collect(Collectors.toSet());
    }

}
