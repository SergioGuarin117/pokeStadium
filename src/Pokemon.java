public class Pokemon{
    private String nombre;
    private String tipos;
    private String sprite;
    private Integer hp;
    private Integer ataque;
    private Integer defensa;
    private Integer velocidad;
    private Integer hpActual;

    public Pokemon(String nombre, String tipos,String sprite,
                   int hp, int ataque, int defensa, int velocidad ){

        this.nombre = nombre;
        this.tipos = tipos;
        this.sprite = sprite;
        this.hp = hp;
        this.hpActual = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;

    }

    public String getNombre() {
        return nombre;
    }

    public String getTipos() {
        return tipos;
    }

    public String getSprite() {
        return sprite;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public Integer getDefensa() {
        return defensa;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public Integer getHpActual() {
        return hpActual;
    }

    public void recibirDaño(int daño){
        hpActual = hpActual - daño;

        if (hpActual <= 0)
            hpActual = 0;
    }

}
