package tvClasses.actionClasses;

import inputClasses.ActionInput;
import inputClasses.InputData;
import tvClasses.TV;
import tvClasses.pageClasses.*;

public interface PageVisitor {
    void visit(final LoginPage loginPage, final ActionInput actionInput, final TV tv);
    void visit(final RegisterPage registerPage, final ActionInput actionInput, final TV tv);
    void visit(final MoviesPage moviesPage, final ActionInput actionInput, final TV tv);
    void visit(final SeeDetailsPage seeDetailsPage, final ActionInput actionInput, final TV tv);
    void visit(final UpgradesPage upgradesPage, final ActionInput actionInput, final TV tv);
    void visit(final LogoutPage logoutPage, final ActionInput actionInput, final TV tv);
    void visit(final HomepageAuth homepageAuth, final ActionInput actionInput, final TV tv);
    void visit(final HomepageNon homepageNon, final ActionInput actionInput, final TV tv);
}
