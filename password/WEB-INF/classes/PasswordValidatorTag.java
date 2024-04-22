package com.example.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorTag extends SimpleTagSupport {
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void doTag() throws JspException, IOException {
        // Regular expression for password validation
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            getJspContext().getOut().write("Password is valid!");
        } else {
            getJspContext().getOut().write("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
    }
}
