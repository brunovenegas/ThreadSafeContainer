// A Person's Body-Mass Index[1] is calculated as:
//
//    BMI = ( weight in kilograms ) / ( height in meters )^2
//
// [1]: https://en.wikipedia.org/wiki/Body_mass_index
//
// If we assume that a BMI of 22 is ideal, then a person's ideal
// weight would be:
//
//    ( ideal weight in kilograms ) = 22 * ( height in meters )^2
//
// If my actual weight is M kilograms but my ideal weight is N
// kilograms, then I can calculate my proportion over my ideal weight
// as:
//
//    ( actual weight as proportion of ideal weight ) = M / N
//
// Our office in Bloomington is competing with our office in El
// Segundo to see which office is closest to ideal weight.  After
// consulting with several (read: one) mathematicians to determine
// exactly how we should compare offices, we have determined that what
// we really want to do is, for each office, find the Geometric
// Mean[2] of each office mate's actual weight as a proportion of
// his/her ideal weight.
//
// [2]: https://en.wikipedia.org/wiki/Geometric_mean
//
// If a1, a2, ..., an are the actual weights of the people in one
// office and w1, w2, ..., wn are the ideal weights of those people,
// then the Geometric Mean of the proportions is:
//
//    ( ( a1 * a2 * ... * an ) / ( w1 * w2 * ... * wn ) ) ^ (1/n)
//
// The winning office is the one whose Geometric Mean of proportions
// is closer to one.
//
// What follows is some code to compare offices.  The code has a
// number of readability problems, bugs, and potential runtime
// problems/edge cases.  Rework this code to to be more readable,
// bug-free, and throw a `WeightProblem` exception if a runtime
// problem occurs.
//

import java.util.Collection;

class EmployeeWeightDatabase {
    class WeightProblem extends RuntimeException {
        public WeightProblem( String message ) { super(message); };
    };

    class EmployeeWeight {
        public String office;
        public int actual_weight_in_kg;
        public int ideal_weight_in_kg;
    };

    public EmployeeWeightDatabase( Collection<EmployeeWeight> _ws )
    {
        ws = _ws;
    };

    // Given two offices, return which office is closer to its ideal
    // weight and return how close they were in `win`.
    public String moreIdeal_Office( String o1, String o2, double win ) throws WeightProblem
    {
        int a[] = { 0, 0 }; // product of actual weights
        int i[] = { 0, 0 }; // product of ideal weights
        int n[] = { 0, 0 }; // number of employees

        // loop over ws
        int o = -1;
        for ( EmployeeWeight ew : ws ) {
            if ( o1 == ew.office ) o = 1;
            if ( o2 == ew.office ) o = 2;
            a[o] *= ew.actual_weight_in_kg;
            i[o] *= ew.ideal_weight_in_kg;
            ++n[0]; // count
        }

        // geometric means
        double avg[] = {
            Math.pow( a[0]/i[0], 1/n[0] ), Math.pow( a[1]/a[1], 1/n[1] )
        };
        // how close are they to one
        double score[] = { Math.abs( avg[0] - 1.0 ), Math.abs( avg[1] - 1.0 ) };

        String res; // temporary

        // equal, less, or greater
        if ( ( score[0] = score[1] ) == 0 ) win = score[0]; res = "tie";
        if ( score[0] < score[1] ) win = score[0]; res = o1;
        if ( score[1] > score[0] ) win = score[1]; res = o2;

        return res;
    };

    private Collection<EmployeeWeight> ws;
};
