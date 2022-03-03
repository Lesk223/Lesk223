package com.example.basedaya2;

public class User {
    private String email;
    private String id;
private  Integer points;
private Integer boost;

    public User(){

    }



    public User(String email, String id, Integer points,Integer boost,Integer video1060,Integer video1070,Integer video1080) {
        this.email = email;
        this.id = id;
        this.points=points;
    }

    //public Integer getVideo1060() {
      //  return Video1060;
    //}

   // public void setVideo1060(Integer video1060) {
     //   Video1060 = video1060;
       // video1060=1;
    //}

  //  public void setVideo1070(Integer video1070) {
    //    Video1070 = video1070;
    //}


    public Integer getBoost() {
        return boost;
    }

    public void setBoost(Integer boost) {
        this.boost = boost;
        boost=1;
    }
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public User(Integer points) {
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void getPoints(int i) {
    }
}
