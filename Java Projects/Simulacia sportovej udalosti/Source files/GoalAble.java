package Semestralka;


/**
 * Rozhranie na to aby som mohol rozdelit hracov na tych ktori mozu dat gol a ktory nie
 *
 * @author   «meno autora»
 * @version  «verzia alebo dátum»
 */
public interface GoalAble
{
    public void addGol();
    public void addAsistencia();
    public int getPocetGolov();
    public int getPocetAsistenci();
    public int getBody();
}
