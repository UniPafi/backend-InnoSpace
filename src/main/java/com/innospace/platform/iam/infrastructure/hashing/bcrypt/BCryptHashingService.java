package com.innospace.platform.iam.infrastructure.hashing.bcrypt;

import com.innospace.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
