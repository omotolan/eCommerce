package africa.semicolon.ecommerce.dto.responses;


import africa.semicolon.ecommerce.dto.UserDto;

public class SignUpResponse extends Response {
    private final UserDto userDto;
    public SignUpResponse(String message, UserDto userDto){
        super(message);

        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "userDto=" + userDto +
                '}';
    }
}
