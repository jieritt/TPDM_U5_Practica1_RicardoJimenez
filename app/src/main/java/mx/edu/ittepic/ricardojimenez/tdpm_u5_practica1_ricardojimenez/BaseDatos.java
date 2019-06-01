package mx.edu.ittepic.ricardojimenez.tdpm_u5_practica1_ricardojimenez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ZODIACO(idZodiaco VARCHAR(30) PRIMARY KEY NOT NULL, " +
                " mensaje VARCHAR(500))");
        String sql = "INSERT INTO ZODIACO VALUES" +
                "('aries','Con la orientación de este día, tendrás buen humor y te sentirás en forma. En el trabajo, podrás realizar tus tareas rápida y fácilmente. Vivenciarás varios momentos agradables con colegas y clientes. En el hogar, estarás de humor para celebrar tu conexión con los miembros de la familia. Cocinarás una cena especial o invitarás a algunas personas para que compartan tu buena energía.')," +
                "('tauro','Hoy tu energía será equilibrada y armoniosa. Sentirás apertura a comunicarte con tus compañeros de trabajo y compartir tiempo con tu familia luego de la jornada laboral. La energía astral te ofrecerá un sentimiento optimista sobre la vida. Es un buen día para contar todas tus bendiciones, y reconocer que es una gran aventura el estar vivo en estos emocionantes tiempos.')," +
                "('geminis','Hoy tus poderes de intuición se reforzarán al sentir los efectos de la energía astral funcionando. Tu sensibilidad a lo que te rodea será fuerte. Te encontrarás anticipando lo que alguien estará por decir, o previendo algo que estará a punto de ocurrir. Este sentido de sintonizar con un radar psíquico continuará durante todo el día, por lo tanto presta atención a tus instintos.')," +
                "('cancer','Tendrás que tener un poco más de cuidado con tus palabras. En este día las personas estarán más sensibles que usualmente. Tus compañeros de trabajo estarán susceptibles, y no aceptarán los comentarios críticos o mordaces. Tienes un maravilloso sentido del humor, pero a veces tu lado ácido puede irritar a los demás. Ten cuidado y trata de comprender, así podrás terminar el día sin tropiezos.')," +
                "('leo','Tu imaginación parece recuperarse el día de hoy, ya que ideas para nuevos proyectos creativos aparecen por montones en tu cabeza. La energía astral abre nuevas posibilidades para la autoexpresión. La parte negativa es que puedes tener demasiadas ideas en las que trabajar de golpe. Haz una lista y utilízala para decidir en cuáles deseas trabajar y guarda las restante para más adelante.'),"+
                "('virgo','Hoy tendrás en mente tu ser físico, ya que la configuración planetaria creará un deseo para dejar atrás todas las debilidades, así como también un nuevo deseo de trabajar para obtener una salud óptima. Desearás explorar el tema de la nutrición, ejercitación, vitaminas, y suplementos de hierbas, así como otras posibilidades como los masajes. Es un momento maravilloso para comenzar con un nuevo programa de salud. Consulta libros, ve al gimnasio, ¡manos a la obra!')," +
                "('libra','Las personas de tu signo son sensibles y creativas. Hoy tus talentos naturales aumentarán ya que la configuración planetaria otorga energía extra. Es un buen momento para comprender ciertas cosas. Quizás necesites aclarar la dirección de como llevas tu relación. Puede que tengas una premonición. O puede que necesites solucionar una situación laboral. Tu intuición te ayudará a solucionar cualquiera que ésta sea.')," +
                "('escorpion','Hoy estarás muy consciente de las emociones ajenas. Por ejemplo, si tus hijos pelean por un juguete, tus nervios te harán explotar de ira. Tus compañeros de trabajo también te molestarán con sus comentarios. Intenta controlar esta tendencia a la sobre reacción. Con la configuración planetaria actual, tu sistema nervioso será como una esponja, absorbiendo energía de las personas que están a tu alrededor. Intenta mantenerte en calma, concéntrate solo en la solución.')," +
                "('sagitario','Hoy el aspecto a tener en cuenta será el aumento de tus emociones, la exageración de los altos y bajos que forman parte de la vida cotidiana. No te resultará fácil manejar tus sentimientos. Te gusta sentir en grande y enérgicamente. No te involucres con pequeños incidentes. Sólo date cuenta de que hoy eres demasiado sensible a las presiones, intenta ser amable con tu cuerpo.')," +
                "('capricornio','Hoy te ocurrirán algunas coincidencias extrañas. Te encontrarás con alguien que no veías desde hace tiempo en el preciso momento en que pensabas en esa persona. O posiblemente recibas una llamada telefónica de alguien en quien pensabas. Estas conexiones psíquicas entre tú y los demás estarán reforzadas debido a la configuración astral. Gozas de tener mucha intuición, ¡pero no siempre confías en ella!')," +
                "('acuario','Algunos ciclos de tu vida se han completado, y hoy tendrás la sensación de que algo importante finalmente ocurrirá. Quizás un proyecto que perseguías en lo laboral se ha estabilizado. O puede que una relación en la que has estado trabajando finalmente muestre signos de progreso. Con esta influencia del día, algo nuevo y positivo aparecerá en tu vida.')," +
                "('picis','Hoy tendrás charlas poco usuales y perspicaces con las personas. La energía astral te incitará a compartir momentos íntimos con tus compañeros de trabajo y tu familia. Se expresarán cosas profundas y poderosas. Si prestas atención, aprenderás mucho sobre las personas en tu vida. Como las personas se abrirán a ti mostrando sus sentimientos íntimos, debes de ser capaz de comprenderlos y confortarlos.')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
