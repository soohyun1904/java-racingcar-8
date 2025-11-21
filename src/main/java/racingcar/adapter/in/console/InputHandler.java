package racingcar.adapter.in.console;

import java.util.List;

public class InputHandler {
    private final InputView inputView;
    private final InputConverter converter;

    public InputHandler(InputView inputView, InputConverter converter) {
        this.inputView = inputView;
        this.converter = converter;
    }

    public List<String> readCarNameStrings(){
        String raw = inputView.readCarNames();
        return converter.toCarNames(raw);
    }

    public int readTryCountValue() {
        String raw = inputView.readTryCount();
        return converter.toTryCount(raw);
    }
}
