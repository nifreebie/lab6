package org.example.client.utils;

import org.example.client.command_builders.CommandBuilder;
import org.example.client.response_handlers.ResponseHandler;
import org.example.contract.command.Command;
import org.example.contract.exceptions.ExtraArgumentException;
import org.example.contract.exceptions.NoArgumentException;
import org.example.contract.responses.Response;
import org.example.contract.utils.BufferedLineReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;

public class ConsoleManager {
    private final Sender sender;


    public ConsoleManager(Sender sender) {
        this.sender = sender;
    }

    public void run(){
        ClientAppContainer app = ClientAppContainer.getInstance();
        BufferedLineReader bufferedLineReader = app.getBufferedLineReader();
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
            String commandName = parseToCommandName(str[0]);
            try {
                CommandBuilder command_builder = (CommandBuilder) Class.forName("org.example.client.command_builders." + commandName + "CommandBuilder").getConstructor().newInstance();
                Command command = command_builder.build(str);
                ResponseHandler responseHandler = (ResponseHandler) Class.forName("org.example.client.response_handlers." + commandName + "ResponseHandler").getConstructor().newInstance();
                try{
                    System.out.println(responseHandler.handleResponse(sender.sendRequest(command)));
                }catch(SocketException e){
                    System.out.println("Сервер не запущен!");
                }
                System.out.print('>');
            } catch (NoClassDefFoundError | ClassNotFoundException e) {
                System.out.println("Команды " + str[0] + " не существует!");
                System.out.print('>');
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IOException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause.getClass().equals(ExtraArgumentException.class)) {
                    System.out.println("У команды не должно быть аргумета!");
                }
                if (cause.getClass().equals(NoArgumentException.class)) {
                    System.out.println("У команды должен быть аргумет!");
                }
                if (cause.getClass().equals(NumberFormatException.class)) {
                    System.out.println("Неверный формат аргумента!");
                }
                System.out.print('>');
            }

        }

    }
    public static String parseToCommandName(String str) {
        char[] chars = str.toCharArray();
        String commandName = "";
        commandName += Character.toUpperCase(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '_') {
                commandName += Character.toUpperCase(chars[i + 1]);
                i++;
            } else {
                commandName += chars[i];
            }

        }
        return commandName;
    }
}