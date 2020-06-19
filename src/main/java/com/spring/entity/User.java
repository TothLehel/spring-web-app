package com.spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@NoArgsConstructor
@Data
@Component
@Entity
public class User {

    @Column(unique = true, nullable = false, length = 20)
    @Id
    @Size(min = 3,max = 20, message = "Your username has to be between 3 and 20 characters!")
    @NotEmpty(message = "You cannot leave this field empty!") //itt egy properties fájlból kéne kiolvasni az értékeket
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "You cannot leave this field empty!")
    @Pattern(regexp = "([A-Z][a-z]*)([\\\\s\\\\\\'-][A-Z][a-z]*)*", message = "Your first name format is not appropriate!")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "If you dont have a last name then write \"Solo\" here")
    @Pattern(regexp = "([A-Z][a-z]*)([\\\\s\\\\\\'-][A-Z][a-z]*)*", message = "Your last name format is not appropriate!")
    private String lastName;

    @Column(nullable = false, length = 4)
    @NotEmpty(message = "You cannot leave this field empty!")
    @Size(max = 4, message = "That's too long for a zip code!") //vagy hát gondolom
    private String zipCode;

    @Column(nullable = false)
    @NotEmpty(message = "You cannot leave this field empty!")
    private String city;

    @Column(nullable = false)
    @NotEmpty(message = "You cannot leave this field empty!" )
    private String street;

    @Column(nullable = false)
    @PositiveOrZero(message = "This field cannot be less then 0!")
    @NotNull(message = "You cannot leave this field empty!" )
    private Integer houseNumber;

    @Column
    @PositiveOrZero(message = "This field cannot be less then 0!")
    private Integer floor;

    @Column
    @PositiveOrZero(message = "This field cannot be less then 0!")
    private Integer door;

    //egy custom validator szebb megoldás lenne ide
    @Column(unique = true)
    @Pattern(regexp = "(null|((?:\\+?3|0)6)(?:-|\\()?(\\d{1,2})(?:-|\\))?(\\d{3})-?(\\d{3,4}))",message = "The phone number format is invalid!" )
    private String mobileNumber;

    @Pattern(regexp = "(null|((?:\\+?3|0)6)(?:-|\\()?(\\d{1,2})(?:-|\\))?(\\d{3})-?(\\d{3,4}))",message = "The phone number format is invalid!" )
    @Column(unique = true)
    private String homeNumber;

}
