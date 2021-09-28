package com.sytoss.model.communication;

import com.sytoss.model.education.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "communication_type")
@Table(name = "communication")
public class Communication {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id")
    private UserAccount sender;

    @Column(name = "send_date")
    private Date sendDate = new Date();

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "update_date")
    private Date updateDate = new Date();

    @Column(name = "active")
    private Boolean active;


    @Transient
    public String getDiscriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }


}
