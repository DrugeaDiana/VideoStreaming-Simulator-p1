package tvclasses.actionclasses;

import inputclasses.ActionInput;
import tvclasses.TV;


public final class ActionHandler {
    private final OnPageVisitor onPage = new OnPageVisitor();
    private final ChangePageVisitor changePage = new ChangePageVisitor();

    /**
     * The function takes each action from the actionInput variable and calls the
     * specific visitor depending on what type of action it is, either "on page"
     * where it calls the OnPageVisitor or "change page" where it calls
     * the ChangePageVisitor.
     * @param actionInput command given
     * @param tv main variable
     */
    public void actionParsing(final ActionInput actionInput, final TV tv) {
        if (actionInput.getType().equals("on page")) {
            tv.getActivePage().accept(onPage, actionInput, tv);
        } else {
            tv.getActivePage().accept(changePage, actionInput, tv);
        }
    }
}
