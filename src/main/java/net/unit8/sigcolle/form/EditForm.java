package net.unit8.sigcolle.form;

import lombok.Data;
import net.unit8.sigcolle.validator.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author maeken
 */
@Data
public class EditForm extends FormBase {

    @DecimalMin("1")
    @DecimalMax("9999")
    private String userId;

    @NotNull
    @Length(min = 1, max = 20)
    private String firstName;

    @NotNull
    @Length(min = 1, max = 20)
    private String lastName;

    @NotNull
    @Length(min = 1, max = 50)
    @Email
    private String email;

    @NotNull
    @Length(min = 4, max = 20)
    @Password
    private String pass;
}
