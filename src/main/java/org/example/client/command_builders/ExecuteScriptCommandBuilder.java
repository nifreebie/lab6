package org.example.client.command_builders;

import lombok.NoArgsConstructor;
import org.example.client.utils.ClientAppContainer;
import org.example.client.utils.CommandParser;
import org.example.client.utils.ConsoleManager;
import org.example.contract.command.Command;
import org.example.contract.command.ExecuteScriptCommand;
import org.example.contract.exceptions.ExtraArgumentException;
import org.example.contract.exceptions.NoArgumentException;
import org.example.contract.exceptions.StopExecuteScriptException;
import org.example.contract.utils.BufferedLineReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ExecuteScriptCommandBuilder implements CommandBuilder {
    @Override
    public Command build(String[] str) {
        if(str.length == 1) throw new NoArgumentException();
        else{
            File scriptFile = new File(str[1]);
            List<Command> commandList = new ArrayList<>();
            if(ClientAppContainer.getInstance().getScriptsStack().contains(scriptFile)){
                System.out.println("Попытка вызвать скрипт, который уже исполняется!");
            }else{
                ClientAppContainer.getInstance().getScriptsStack().add(scriptFile);
                try(FileInputStream input = new FileInputStream("src/main/java/org/example/client/data/" + scriptFile+".txt")){
                    BufferedLineReader bufferedLineReader = new BufferedLineReader(input);
                    ClientAppContainer.getInstance().setBufferedLineReader(bufferedLineReader);
                    while(bufferedLineReader.hasNextLine()){
                        try{
                            String line = bufferedLineReader.nextLine().trim();
                            String[] strScript = line.trim().split("\\s+");
                            while (strScript.length == 0 || strScript.length > 2) {
                                System.out.println("Неверный формат ввода команды!");
                                System.out.println(">");
                                line = bufferedLineReader.nextLine().trim();
                                strScript = line.trim().split("\\s+");

                            }
                            try {
                                commandList.add(CommandParser.getDTO(strScript));
                            } catch (NullPointerException | ClassNotFoundException e) {
                                System.out.println("Команды " + str[0] + " не существует!!");
                            }catch(NoArgumentException e){
                                System.out.println("У команды должен быть аргумет!");
                            }
                            catch(ExtraArgumentException e){
                                System.out.println("У команды не должно быть аргумета!");
                            }catch(NumberFormatException e){
                                System.out.println("Неверный формат аргумента!");
                            }
                        }catch(StopExecuteScriptException e){
                            System.out.println("Ошибка при исполнении скрипта!");
                            break;

                        }
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Такого файла не существует!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ClientAppContainer.getInstance().getScriptsStack().removeLast();

            }
            return new ExecuteScriptCommand(commandList);
        }
    }
}
