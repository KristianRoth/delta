package fi.delta.delta.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonRootName
import javax.persistence.*
import javax.validation.constraints.Size


@Entity
@JsonRootName("user")
data class User(

        @Column(unique = true, nullable = false)
        var email: String,
        @Column(unique = true, nullable = false)
        @Size(min=4)
        var username: String,
        @JsonIgnore
        var password: String,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0

) {



}