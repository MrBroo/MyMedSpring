package byfayzullayev.mymed.service.user;


import byfayzullayev.mymed.entity.role.RoleEnum;
import byfayzullayev.mymed.entity.user.UserEntity;
import byfayzullayev.mymed.model.receive.user.SignInReceiveModel;
import byfayzullayev.mymed.model.receive.user.SignUpReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.repository.RoleRepository;
import byfayzullayev.mymed.repository.UserRepository;
import byfayzullayev.mymed.service.base.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;
    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
    }

    public ApiResponse addUser(SignUpReceiveModel signUpReceiveModel) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(signUpReceiveModel.getUsername());
        if (optionalUserEntity.isPresent())
            return USER_EXIST;

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpReceiveModel.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signUpReceiveModel.getPassword()));
        userEntity.setPhoneNumber(signUpReceiveModel.getPhoneNumber());

        if (signUpReceiveModel.getRoleEnum() == null)
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(RoleEnum.USER)));
        else
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(signUpReceiveModel.getRoleEnum())));
        userRepository.save(userEntity);
        return SUCCESS_V2;
    }

    public ApiResponse login(
            SignInReceiveModel signInReceiveModel
    ) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(signInReceiveModel.getUsername());
        if (optionalUserEntity.isEmpty())
            return USER_NOT_FOUND;
        String token = this.generateToken(optionalUserEntity.get());
        SUCCESS.setData(token);
        return SUCCESS;


    }

    public ApiResponse getUsersList() {
        SUCCESS.setData(userRepository.findAll());
        return SUCCESS;
    }


    private String generateToken(UserEntity userEntity) {
        try {
            return Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .setSubject(userEntity.getUsername())
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }
}
