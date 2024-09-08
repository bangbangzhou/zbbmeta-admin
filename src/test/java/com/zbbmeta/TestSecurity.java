package com.zbbmeta;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSecurity {

    @Test
    public void test(){
        String zbbmeta = SecureUtil.md5("zbbmeta");
        System.out.println("zbbmeta = " + zbbmeta);

    }
}



