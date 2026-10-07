public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private final BattleListener listener;

    public Battle(Pokemon pokemon1, Pokemon pokemon2, BattleListener listener){
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.listener = listener;
    }

    // Se encarga de elegir cual pokemon tiene la iniciativa en la batalla
    public Pokemon IniciativaPokemon(Pokemon pokemon1, Pokemon pokemon2){
         if (pokemon1.getVelocidad()>pokemon2.getVelocidad()){
             return pokemon1;
         }else if (pokemon1.getVelocidad() < pokemon2.getVelocidad()){
             return pokemon2;
         }else{
             return Math.random() < 0.5 ? pokemon1 : pokemon2;
         }
    }
    // Es la encargada del daño de cada turno
    public int atacar(Pokemon atacante, Pokemon defensor) {

        double randomAtaque = Math.random();
        double randomDefensa = Math.random();

        double resultado = atacante.getAtaque()* randomAtaque - defensor.getDefensa()*randomDefensa;

        if (resultado<1){
            listener.enTurno(atacante.getNombre(),defensor.getNombre(),0,false,1.0);
            return 0;
        }
        int daño = (int) resultado;
        listener.enTurno(atacante.getNombre(), defensor.getNombre(), daño, false, 1.0);
        defensor.recibirDaño(daño);
        listener.cambioHp(defensor.getNombre(), defensor.getHpActual());

        if (defensor.getHpActual() <= 0) {
            listener.finalizarBatalla(atacante.getNombre());

        }
        return daño;
    }
}
