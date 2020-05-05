package de.my5t3ry.googlecli;

import de.my5t3ry.googlecli.command.AbstractCommand;
import de.my5t3ry.googlecli.command.CommandService;
import de.my5t3ry.googlecli.config.PropertiesService;
import de.my5t3ry.googlecli.history.GoogleSearchCliHistory;
import de.my5t3ry.googlecli.print.Printer;
import de.my5t3ry.googlecli.term.TerminalService;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;

class GoogleSearchCliMain {

  public static void main(String[] args) {

    PropertiesService.loadProperties();
    CommandService.initCommands();
    TerminalService.init();
    LineReader lineReader =
        LineReaderBuilder.builder()
            .terminal(TerminalService.terminal)
            .history(new GoogleSearchCliHistory())
            .build();
    Printer.printStartMessage();
    while (true) {
      String line = null;
      try {
        line = lineReader.readLine("> ");
        if (line.equals(PropertiesService.properties.getProperty("command.exit"))) {
          return;
        }
        for (AbstractCommand curCommand : CommandService.getCommands()) {
          if (curCommand.executesCommand(line)) {
            curCommand.execute(line);
            break;
          }
        }
      } catch (UserInterruptException | EndOfFileException e) {
        return;
      }
    }
  }
}
