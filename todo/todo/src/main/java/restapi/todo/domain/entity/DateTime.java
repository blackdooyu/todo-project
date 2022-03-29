package restapi.todo.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class DateTime {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    @Column(updatable = false)
    private LocalDate creteDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @LastModifiedDate
    private LocalDate updateDate;


}
