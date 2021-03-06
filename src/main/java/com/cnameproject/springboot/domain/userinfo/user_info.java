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
    private Long get_time; // 명함 교환할 때의 시간

    @Column(nullable = true)
    private Double latitude; //위도데이터

    @Column(nullable = true)
    private Double longitude; //경도데이터

    @Column(nullable = true)
    private String friend_id; //친구들 ID

    @Builder
    public user_info(String name, String email, String phone_num, String bluetooth_data, String birth_data,
                     long get_time, double latitude, double longitude, String friend_id) {
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.bluetooth_data = bluetooth_data;
        this.birth_data = birth_data;
        this.get_time = get_time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.friend_id = friend_id;
    }

    public void cname_update(long get_time, double latitude, double longitude){
        this.get_time = get_time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addUpdate(String name, String phone_num,String bluetooth_data,String birth_data){
        this.name = name;
        this.phone_num = phone_num;
        this.bluetooth_data = bluetooth_data;
        this.birth_data = birth_data;
    }

    public void addFriendId(Long friend_id) {
        if (this.friend_id == null){
            this.friend_id = String.valueOf(friend_id);
        }
        else {
            boolean test = false;
            String[] dupleTest = this.friend_id.split("/");
            for (String id : dupleTest) {
                if (id.equals(String.valueOf(friend_id))){
                    test = true;
                    break;
                }
            }
            if (test) {
                this.friend_id = this.friend_id;
            }
            else {
                this.friend_id = this.friend_id + "/" + String.valueOf(friend_id);
            }
        }
    }
}
