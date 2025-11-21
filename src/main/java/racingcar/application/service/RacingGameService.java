package racingcar.application.service;

import racingcar.application.dto.response.GameResultResponseDTO;
import racingcar.application.mapper.ResponseMapper;
import racingcar.application.port.in.GamePlayCommand;
import racingcar.application.port.in.PlayGameUseCase;
import racingcar.application.service.domainfactory.GameDomainFactory;
import racingcar.domain.model.RacingGame;
import racingcar.domain.model.RacingGame.GameResult;
import racingcar.domain.policy.MovePolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RacingGameService implements PlayGameUseCase {
    private final Supplier<MovePolicy> movePolicySupplier;
    private final GameDomainFactory domainFactory;
    private final ResponseMapper responseMapper;

    public RacingGameService(Supplier<MovePolicy> movePolicySupplier) {
        this.movePolicySupplier = movePolicySupplier;
        this.domainFactory = new GameDomainFactory();
        this.responseMapper = new ResponseMapper();
    }

    @Override
    public GameResultResponseDTO play(GamePlayCommand command) {
        RacingGame game = domainFactory.create(command.carNames(), command.tryCount());
        GameResult result = game.play(this::createMovePolicies);
        return responseMapper.toDTO(result);
    }

    private List<MovePolicy> createMovePolicies(int carCount) {
        List<MovePolicy> policies = new ArrayList<>();
        for (int i = 0; i < carCount; i++) {
            policies.add(movePolicySupplier.get());
        }
        return policies;
    }
}
