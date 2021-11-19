package com.cnameproject.springboot.domain.belonginfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class belong_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String belong_data;
    private String position_data;

    @Column(nullable = true)
    private String tel_data;
    private String fax_data;

    @Column(nullable = false)
    private String start_data;

    @Column(nullable = true)
    private String fin_data;

    @Builder
    public belong_info(long user_id, String belong_data, String position_data, String tel_data, String fax_data, String start_data, String fin_data) {
        this.user_id=user_id;
        this.belong_data=belong_data;
        this.position_data=position_data;
        this.tel_data=tel_data;
        this.fax_data=fax_data;
        this.start_data=start_data;
        this.fin_data=fin_data;
    }
}
