package inputClasses;

public class FiltersInput {
    private SortingInput sort;
    private ContainsInput contains;

    public SortingInput getSort() {
        return sort;
    }

    public void setSort(final SortingInput sort) {
        this.sort = sort;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(final ContainsInput contains) {
        this.contains = contains;
    }
}
