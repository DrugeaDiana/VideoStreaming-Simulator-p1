package tvClasses.actionClasses;

import inputClasses.InputData;
import tvClasses.pageClasses.*;

public interface PageVisitor {
    void visit(final LoginPage loginPage, final InputData inputData);
    void visit(final RegisterPage registerPage, final InputData inputData);
    void visit(final MoviesPage moviesPage, final InputData inputData);
    void visit(final SeeDetailsPage seeDetailsPage, final InputData inputData);
    void visit(final UpgradesPage upgradesPage, final InputData inputData);
    void visit(final LogoutPage logoutPage, final InputData inputData);
    void visit(final HomepageAuth homepageAuth, final InputData inputData);
}
