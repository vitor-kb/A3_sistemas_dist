public class Player {
    private String name;
    private int level;
    private String skills;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.skills = "Fireball, Healing";
    }

    public int getLevel() {
        return level;
    }

    public String getSkills() {
        return skills;
    }

    public void levelUp() {
        level++;
        if (level == 5) {
            skills += ", Thunderstrike";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
