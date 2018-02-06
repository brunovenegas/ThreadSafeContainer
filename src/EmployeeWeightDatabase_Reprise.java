import java.util.Collection;

public class EmployeeWeightDatabase_Reprise {

    private Collection<EmployeeWeight> mEmployeeWeightCollection;

    public EmployeeWeightDatabase_Reprise(Collection<EmployeeWeight> weightCollection) {
        mEmployeeWeightCollection = weightCollection;
    }

    public String[] moreIdealOffice(String officeOne, String officeTwo, double winnerCloseness) throws WeightProblem {
        // array set up in this manner: [officeOne, officeTwo]
        int actualWeight[] = {0, 0};
        int idealWeight[] = {0, 0};
        int numOfEmployees[] = {0, 0};

        int office = -1;
        for(EmployeeWeight tempEmployeeWeight : mEmployeeWeightCollection) {
            if(officeOne == tempEmployeeWeight.office) office = 0;
            else if(officeTwo == tempEmployeeWeight.office) office = 1;

            actualWeight[office] *= tempEmployeeWeight.actual_weight_in_kg;
            idealWeight[office] *= tempEmployeeWeight.ideal_weight_in_kg;
            numOfEmployees[office] = numOfEmployees[office] + 1;
        }

        double geometricMean[] = {Math.pow(actualWeight[0]/idealWeight[0], 1/numOfEmployees[0]),
                Math.pow(actualWeight[1]/idealWeight[1], 1/numOfEmployees[1])};

        double score[] = { Math.abs(geometricMean[0] - 1.0), Math.abs(geometricMean[1] - 1.0)};

        // Array set up in the manner: [office, closeness to 1]
        String[] officeAndCloseness = {"0","0"};
        String temp;

        if(score[0] == score[1]) {
            officeAndCloseness[0] = "tie";
            officeAndCloseness[1] = Double.toString(score[0]);
        }
        else if(score[0] < score[1]) {
            officeAndCloseness[0] = officeOne;
            officeAndCloseness[1] = Double.toString(score[0]);
        }
        else if(score[0] > score[1]) {
            officeAndCloseness[0] = officeTwo;
            officeAndCloseness[1] = Double.toString(score[1]);
        }
        else {
            officeAndCloseness = null;
        }

        return officeAndCloseness;
    }


    class EmployeeWeight {
        public String office;
        public int actual_weight_in_kg;
        public int ideal_weight_in_kg;
    }

    class WeightProblem extends RuntimeException {
        public WeightProblem( String message ) {
            super(message); }
    }
}
