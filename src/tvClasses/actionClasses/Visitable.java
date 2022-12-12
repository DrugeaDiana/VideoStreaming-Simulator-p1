package tvClasses.actionClasses;

import inputClasses.ActionInput;
import tvClasses.TV;

public interface Visitable {
    void accept(PageVisitor v, ActionInput actionInput, TV tv);
}
