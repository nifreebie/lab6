package org.example.client.utils;

import org.example.contract.exceptions.ExtraArgumentException;
import org.example.contract.exceptions.NoArgumentException;
import org.example.contract.utils.BufferedLineReader;

import java.io.IOException;

public class ConsoleManager {
    private final Sender sender;


    public ConsoleManager(Sender sender) {
        this.sender = sender;
    }

    public void run(){
        ClientAppContainer app = ClientAppContainer.getInstance();
        BufferedLineReader bufferedLineReader = app.getBufferedLineReader();
        ResponseHandler responseHandler = new ResponseHandler();
        System.out.println("Программа запущена, для получения списка команд введите 'help'");
        System.out.print('>');
        while (bufferedLineReader.hasNextLine()) {
            String line = bufferedLineReader.nextLine().trim();
            String[] str = line.trim().split("\\s+");
            while (str.length == 0 || str.length > 2) {
                System.out.println("Неверный формат ввода команды!");
                System.out.print('>');
                line = bufferedLineReader.nextLine().trim();
                str = line.trim().split("\\s+");

            }
            try {
                System.out.println(responseHandler.handleResponse(sender.sendRequest(CommandParser.getDTO(str))));
                System.out.print('>');
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.out.println("Команды " + str[0] + " не существует!");
            }catch(NoArgumentException e){
                System.out.println("У команды должен быть аргумет!");
                System.out.print('>');
            }
            catch(ExtraArgumentException e){
                System.out.println("У команды не должно быть аргумета!");
                System.out.print('>');
            }catch(NumberFormatException e){
                System.out.println("Неверный формат аргумента!");
                System.out.print('>');
            }

        }

    }
}
