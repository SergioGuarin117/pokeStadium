public interface BattleListener {
    void enTurno(String atacante, String defensor, int daño, boolean critico, double modificador);

    void cambioHp(String pokemon, int hpActual);

    void finalizarBatalla(String ganador);
}
