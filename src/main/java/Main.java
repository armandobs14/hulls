
import br.com.nees.theadcaller.ThreadManager;
import br.com.nees.theadcaller.ThreadNotifier;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author armando
 */
public class Main {
    public static void main(String[] args) {
        
        ThreadManager manager = ThreadManager.getInstance();

        ThreadNotifier thread = new KaoThread(1);
        thread.addListener(manager);
        thread.start();
//        ConcaveHullBuilder builder = new ConcaveHullBuilder();
//        builder.demo();
                
    }
}
