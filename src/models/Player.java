package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Player {
    private Long id;
    private String name;
    private Integer time;
    private Integer level;
    private String type;

    public Player(Long id, String name, Integer time, Integer level, String type) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.level = level;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
