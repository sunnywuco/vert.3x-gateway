/*
The MIT License (MIT)
Copyright (c) 2016 stone
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.stone.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
public class ConfigLoader {

    @Value("${msg:defaultMsg}")
    private String msg;

    @Value("${smsNotifyUrl:defaultSmsNotifyUrl}")
    private String smsNotifyUrl;

    @Value("${smsPassword:defaultSmsPassword}")
    private String smsPassword;

    @Value("${smsAccount:defaultSmsAccount}")
    private String smsAccount;

    @Value("${first.second:tree}")
    private String tree;


    public String getSmsAccount() {
        return smsAccount;
    }

    public String getSmsNotifyUrl() {
        return smsNotifyUrl;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public String getMsg() {
        return "Hello " + this.msg + " " + this.tree + " ";
    }
}
