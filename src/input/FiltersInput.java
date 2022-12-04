package input;

public class FiltersInput {
    private SortingInput sort;
    private ContainsInput contains;

    public SortingInput getSort() {
        return sort;
    }

    public void setSort(SortingInput sort) {
        this.sort = sort;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(ContainsInput contains) {
        this.contains = contains;
    }
}
