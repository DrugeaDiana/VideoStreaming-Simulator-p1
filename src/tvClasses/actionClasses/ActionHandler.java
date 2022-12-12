package tvClasses.actionClasses;

import inputClasses.ActionInput;
import tvClasses.TV;


public class ActionHandler {
    private final OnPageVisitor onPage = new OnPageVisitor();
    private final ChangePageVisitor changePage = new ChangePageVisitor();
    public void actionParsing(ActionInput actionInput, TV tv) {
        if (actionInput.getType().equals("on page")) {
            //System.out.println("works hewwo?");
            tv.getActivePage().accept(onPage, actionInput, tv);
        } else {
            //System.out.println("maybe not?");
            tv.getActivePage().accept(changePage, actionInput, tv);
        }
    }
}
