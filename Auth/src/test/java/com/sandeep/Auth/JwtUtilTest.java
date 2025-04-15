//package com.sandeep.Auth;
//
//import com.sandeep.Auth.JwtConfig.JwtHelper;
//import io.jsonwebtoken.security.Keys;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.crypto.SecretKey;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class JwtUtilTest {
//    private JwtHelper jwtHelper;
//    private String secretKey="afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
//    private SecretKey key;
//
//    @BeforeEach
//    void setUp(){
//        jwtHelper=new JwtHelper(secretKey);
//        key= Keys.hmacShaKeyFor(secretKey.getBytes());
//    }
//    @Test
//    void testGenerateToken(){
//        String token = jwtHelper.generateToken("testuser");
//        assertNotNull(token,"token should not be null");
//        assertTrue(token.length()>10,"token length should be greater than 10");
//
//    }
//
//}
