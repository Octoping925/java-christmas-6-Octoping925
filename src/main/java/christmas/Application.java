package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.badge.BadgeFactory;
import christmas.domain.discount.*;
import christmas.domain.gift.GiftDispenser;
import christmas.facade.ChristmasEventFacade;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Application {
    public static void main(String[] args) {
        Supplier<String> consoleReadSupplier = Console::readLine;
        Consumer<String> consolePrintConsumer = System.out::println;
        Supplier<LocalDate> currentDateSupplier = LocalDate::now;

        ChristmasEventFacade facade = new ChristmasEventFacade(
                new DiscountCalculator(
                        new ChristmasDiscountPolicy(currentDateSupplier),
                        new WeekdayDiscountPolicy(currentDateSupplier),
                        new WeekendDiscountPolicy(currentDateSupplier),
                        new SpecialDiscountPolicy(currentDateSupplier)
                ),
                new GiftDispenser(),
                new BadgeFactory(),
                new InputView(consoleReadSupplier, consolePrintConsumer),
                new OutputView(consolePrintConsumer)
        );

        facade.run();
    }
}
