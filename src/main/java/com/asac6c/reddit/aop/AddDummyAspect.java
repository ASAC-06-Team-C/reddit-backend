package com.asac6c.reddit.aop;

import com.asac6c.reddit.entity.UserEntity;
import com.asac6c.reddit.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class AddDummyAspect {

    private final UserEntityRepository userEntityRepository;

    @Around("@annotation(AddDummyUser)")
    public Object addDummy(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        AddDummyUser adu = ms.getMethod().getAnnotation(AddDummyUser.class);
        UserEntity dummyUser = UserEntity.from(adu.type());
        userEntityRepository.save(dummyUser);
        try {
            return joinPoint.proceed();
        } finally {
        }
    }
}
