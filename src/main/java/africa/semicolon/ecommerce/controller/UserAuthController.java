package africa.semicolon.ecommerce.controller;


import africa.semicolon.ecommerce.data.model.User;
import africa.semicolon.ecommerce.dto.requests.LoginRequest;
import africa.semicolon.ecommerce.dto.requests.SignUpRequest;
import africa.semicolon.ecommerce.exceptions.TokenException;
import africa.semicolon.ecommerce.exceptions.UserAlreadyExistException;
import africa.semicolon.ecommerce.exceptions.UserDoesNotExistException;
import africa.semicolon.ecommerce.security.jwt.TokenProvider;
import africa.semicolon.ecommerce.services.UserAuthService;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class UserAuthController {
    private final UserAuthService userAuthService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {

            var serviceResponse = userAuthService.signUp(signUpRequest);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (UnirestException | UserAlreadyExistException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/confirm/{token}")
    public ResponseEntity<?> confirm(@PathVariable String token) {
        try {
            var serviceResponse = userAuthService.confirmToken(token);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        } catch (TokenException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping( "/user/{id}")
//    public ResponseEntity<?> confirm( @PathVariable String id) {
//        try {
//            var serviceResponse = userAuthService.findById(Long.parseLong(id));
//            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
//            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
//        }catch ( TokenException e){
//            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
//            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateJWTToken(authentication);
            User user = userAuthService.getUserByEmail(loginRequest.getEmail());
            return new ResponseEntity<>(new AuthToken(token, user.getId()), HttpStatus.OK);
        } catch (BadCredentialsException | UserDoesNotExistException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }


    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }));
        return errors;
    }

}
