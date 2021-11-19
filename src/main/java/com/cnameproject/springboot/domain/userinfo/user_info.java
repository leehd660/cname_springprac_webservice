package com.cnameproject.springboot.domain.userinfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class user_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String email;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String phone_num;
    private String bluetooth_data;

    @Column(nullable = true)
    private String birth_data;

    @Column
    private long getTime; // 명함 교환할 때의 시간

    @Column(nullable = true)
    private double latitude; //위도데이터

    @Column(nullable = true)
    private double longitude; //경도데이터

    @Column(nullable = true)
    private String friendId; //친구들 ID

    @Builder
    public user_info(String name, String email, String phone_num, String bluetooth_data, String birth_data,
                     long getTime, double latitude, double longitude, String friendId) {
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.bluetooth_data = bluetooth_data;
        this.birth_data = birth_data;
        this.getTime = getTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.friendId = friendId;
    }

    public void cname_update(long getTime, double latitude, double longitude){
        this.getTime = getTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addUpdate(String name, String phone_num,String bluetooth_data,String birth_data){
        this.name = name;
        this.phone_num = phone_num;
        this.bluetooth_data = bluetooth_data;
        this.birth_data = birth_data;
    }

    public void addFriendId(Long friendId) {
        if (this.friendId == null){
            this.friendId = String.valueOf(friendId);
        }
        else {
            this.friendId = this.friendId+ "/" + String.valueOf(friendId);
        }
    }
}
