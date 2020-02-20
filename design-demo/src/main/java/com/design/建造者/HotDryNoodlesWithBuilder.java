package com.design.建造者;

/**
 * @author: zhoujian
 * @Date: 2019/10/12 13:41
 * @Company: youanmi.
 * @Desc:
 */
public class HotDryNoodlesWithBuilder {

    private boolean addShallot;
    private boolean addParsley;
    private boolean addChili;
    private boolean addSauerkraut;

    public HotDryNoodlesWithBuilder(Builder builder) {
        this.addShallot = builder.addShallot;
        this.addParsley = builder.addParsley;
        this.addChili = builder.addChili;
        this.addSauerkraut = builder.addSauerkraut;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("A bowl of hot-dry noodles has:");

        if (this.addShallot) {
            builder.append("葱花.");
        }

        if (this.addParsley) {
            builder.append("香菜.");
        }

        if (this.addChili) {
            builder.append("辣椒.");
        }

        if (this.addSauerkraut) {
            builder.append("酸菜.");
        }

        return builder.toString();
    }


    public static void main(String[] args) {



        // with builder
        HotDryNoodlesWithBuilder noodlesC = new HotDryNoodlesWithBuilder.Builder()
                .withChili()
                .withParsley()
                .build();
        System.out.println("Customer C wants: " + noodlesC);

        HotDryNoodlesWithBuilder noodlesD = new HotDryNoodlesWithBuilder.Builder()
                .withChili()
                .withParsley()
                .withSauerkraut()
                .withShallot()
                .build();
        System.out.println("Customer D wants: " + noodlesD);
    }


    public static class Builder {

        private boolean addShallot;
        private boolean addParsley;
        private boolean addChili;
        private boolean addSauerkraut;

        public Builder() {

        }

        public Builder withShallot() {
            this.addShallot = true;
            return this;
        }

        public Builder withParsley() {
            this.addParsley = true;
            return this;
        }

        public Builder withChili() {
            this.addChili = true;
            return this;
        }

        public Builder withSauerkraut() {
            this.addSauerkraut = true;
            return this;
        }

        public HotDryNoodlesWithBuilder build() {
            return new HotDryNoodlesWithBuilder(this);
        }
    }

}