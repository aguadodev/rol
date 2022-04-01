package rol;

public class Personaje {
    String nombre;
    // Raza
    enum Raza {HUMANO, ELFO, ENANO, HOBBIT, ORCO, TROLL}
    Raza raza;
    // Atributos físicos
    int fuerza;
    int agilidad;
    int constitucion;
    int inteligencia;
    int intuicion ;
    int presencia;
    // Nivel, experiencia y PV
    int nivel;
    int experiencia;
    int puntosVida;

    // CONSTRUCTORES
    public Personaje(String nombre, Raza raza, int fuerza, int agilidad, int constitución, int inteligencia, int intuicion, int presencia, int nivel, int experiencia, int puntosVida) {
        this.nombre = nombre;
        this.raza = raza;
        this.fuerza = fuerza>0?fuerza:0;
        this.agilidad = agilidad>0?agilidad:0;
        this.constitucion = constitución>0?constitución:0;
        this.inteligencia = inteligencia>0?inteligencia:0;
        this.intuicion = intuicion>0?intuicion:0;
        this.presencia = presencia>0?presencia:0;
        this.nivel = nivel>0?nivel:0;
        this.experiencia = experiencia>0?experiencia:0;
        this.puntosVida = puntosVida>0?puntosVida:0;
    }

    public Personaje(String nombre, Raza raza, int fuerza, int agilidad, int constitucion, int inteligencia, int intuicion, int presencia) {
        this(nombre, raza, fuerza, agilidad, constitucion, inteligencia, intuicion, presencia, 1, 0, 50+constitucion);
    }
    
    public Personaje(String nombre, Raza raza) {
        this(nombre, raza, random100(), random100(), random100(), random100(), random100(), random100());
    }

    public Personaje(String nombre) {
        this(nombre, Raza.HUMANO);
    }

    // MÉTODOS PÚBLICOS
    public void mostrar(){
        System.out.println("PERSONAJE");
        System.out.println("=========");
        System.out.println("Nombre: " + nombre);
    // Raza
        System.out.println("Raza: " + raza);
    // Atributos físicos
        System.out.println("fuerza: " + fuerza);
        System.out.println("agilidad: " + agilidad);
        System.out.println("constitucion: " + constitucion);
        System.out.println("inteligencia: " + inteligencia);
        System.out.println("intuicion: " + intuicion);
        System.out.println("presencia: " + presencia);
    // Nivel, experiencia y PV       
        System.out.println("nivel: " + nivel);
        System.out.println("experiencia: " + experiencia);
        System.out.println("puntosVida: " + puntosVida);
        System.out.println("");
        
    }

    public void subirNivel(){
        nivel++;
    }    
    
    public void curar(){
        if (puntosVida < constitucion + 50){
            puntosVida = constitucion + 50;
        }
    }
    
    public boolean estaVivo(){
        return puntosVida >= 0;
    }
 
    public boolean perderVida(int puntos){
        puntosVida -= puntos;
        return puntosVida < 0;
    }

    public void sumarExperiencia(int puntos){
        experiencia += puntos;
    }
    
    public void atacar(Personaje p){
        System.out.println(nombre + "("+ puntosVida +") ataca a " + 
                p.nombre + "(" + p.puntosVida + "): ");
        int dadosPj = random100();
        int ataque = fuerza + dadosPj;
        System.out.print("* Ataque = (fuerza + random100) = ");
        System.out.println("(" + fuerza + " + " + dadosPj + ") = " + ataque);
        int dadosM = random100();
        int defensa = p.agilidad + dadosM;        
        System.out.print("* Defensa = (defensa + random100) = ");
        System.out.println("(" + p.agilidad + " + " + dadosM + ") = " + defensa);
        
        System.out.print("* Resultado: ");
        int resultado = ataque - defensa;
        
        if (resultado > 0) {
            sumarExperiencia(resultado);
            System.out.println(nombre + " suma " + resultado + " puntos de experiencia.");
            if (p.perderVida(resultado))
                System.out.println(nombre + " mata a " + p.nombre + "!!! (-" + resultado + " PV)");
            else
                System.out.println(nombre + " hiere a " + p.nombre + " (-" + resultado + " PV)");                                   
        } else {
            System.out.println(p.nombre + " esquiva o para el ataque.");
        }                
    }      
    
    
    // MÉTODOS PRIVADOS
    private static int random100(){
        return (int)(Math.random() * 100 + 1);
    }            
}
