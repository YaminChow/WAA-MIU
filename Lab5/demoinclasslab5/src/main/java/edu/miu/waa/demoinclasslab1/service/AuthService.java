package edu.miu.waa.demoinclasslab1.service;

import edu.miu.waa.demoinclasslab1.dto.request.LoginRequest;
import edu.miu.waa.demoinclasslab1.dto.request.RefreshTokenRequest;
import edu.miu.waa.demoinclasslab1.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
