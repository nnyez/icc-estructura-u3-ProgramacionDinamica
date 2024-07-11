package Ejercicios.contorllers;

import java.util.List;
import java.util.*;

import Ejercicios.models.Celda;

/*
 * Un jugador está en la esquina superior izquierda (0,0) de un tablero m x n. En el tablero hay celdas
 * transitables (true) y no transitables (false). Encuentra un camino válido para ir a la esquina
 * inferior izquierda con el jugador, sabiendo que solo se puede mover hacia abajo y hacia la derecha.
 *
 * Ejemplo 1:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,false,false,true]
 *      [true,true,false,true]
 *      [true,true,false,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (0,2), (0,3), (1,3), (2,3), (3,3)]
 *
 * Ejemplo 2:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,true,true,true]
 *      [true,true,false,false]
 *      [true,true,true,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (1,1), (2,1), (3,1), (3,2), (3,3)]
 *
 */
public class Laberinto {

    public List<Celda> getPath(boolean[][] grid) {
        List<Celda> list = new ArrayList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return null;
        }
        if (getPathCaching(grid, list, new HashMap<>(), new Celda(0, 0)))
            return list;
        return new ArrayList<>();

    }

    public boolean getPathCaching(boolean[][] grid, List<Celda> list, HashMap<Celda, Boolean> cache, Celda last) {
        if (last.getRow() >= grid.length || last.getCol() >= grid[0].length
                || !grid[last.getRow()][last.getCol()]) {
            return false;
        }
        if (cache.containsKey(last)) {
            return cache.get(last);
        }

        boolean isEnd = (last.getRow() == grid.length - 1 && last.getCol() == grid[0].length - 1);
        boolean isTruePath = false;

        if (isEnd || getPathCaching(grid, list, cache, new Celda(last.getRow() + 1, last.getCol()))
                || getPathCaching(grid, list, cache, new Celda(last.getRow(), last.getCol() + 1))) {
            list.add(last);
            isTruePath = true;
        }

        cache.put(last, isTruePath);

        return isTruePath;

    }
}