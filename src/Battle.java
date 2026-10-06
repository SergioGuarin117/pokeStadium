public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private final BattleListener listener;

    public Battle(Pokemon pokemon1, Pokemon pokemon2, BattleListener listener){
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.listener = listener;
    }

    public Pokemon IniciativaPokemon(Pokemon pokemon1, Pokemon pokemon2){
         if (pokemon1.getVelocidad()>pokemon2.getVelocidad()){
             return pokemon1;
         }else if (pokemon1.getVelocidad() < pokemon2.getVelocidad()){
             return pokemon2;
         }else{
             return Math.random() < 0.5 ? pokemon1 : pokemon2;
         }
    }

    public int atacar(Pokemon atacante, Pokemon defensor) {
        int daño = Math.max(1, atacante.getAtaque() - defensor.getDefensa() / 2);
        defensor.recibirDaño(daño);
        listener.enTurno(atacante.getNombre(), defensor.getNombre(), daño, false, 1.0);
        listener.cambioHp(defensor.getNombre(), defensor.getHpActual());

        if (defensor.getHpActual() <= 0) {
            listener.finalizarBatalla(atacante.getNombre());

        }
        return daño;
    }

    public void combatir(){
        Pokemon atacante = IniciativaPokemon(pokemon1, pokemon2);
        Pokemon defensor = (pokemon1 == atacante) ? pokemon2:pokemon1;

        while (pokemon1.getHpActual() > 0 && pokemon2.getHpActual()>0) {
            atacar(atacante, defensor);

            if (defensor.getHpActual() <= 0){
                break;
            }
            Pokemon temporal = atacante;
            atacante = defensor;
            defensor = temporal;
        }

    }


}
