package org.jkeltner.model;

public class OrderFlags {
    private final boolean cancelBCond;
    private final boolean placeBOrder;
    private final boolean placeSCond;
    private final boolean placeSOrder;

    public OrderFlags(boolean cancelBCond, boolean placeBOrder, boolean placeSCond, boolean placeSOrder) {
        this.cancelBCond = cancelBCond;
        this.placeBOrder = placeBOrder;
        this.placeSCond = placeSCond;
        this.placeSOrder = placeSOrder;
    }

    public boolean isCancelBCond() {
        return cancelBCond;
    }

    public boolean isPlaceBOrder() {
        return placeBOrder;
    }

    public boolean isPlaceSCond() {
        return placeSCond;
    }

    public boolean isPlaceSOrder() {
        return placeSOrder;
    }

//        if (cancelBcond) {
//        //debugPrint(candle, );
////            logger.info("cancelBcond");
//        //orderFlags.setCancelBcond();
////            logger.info("" + orderFlags.isCancelBcond());
//    }
//
//        if (crossUpper) {
//        //debugPrint(candle, "Go long @ " + bPrice);
////            logger.info("Cross Upper");
////            orderFlags.setPlaceBorder();
////            logger.info("" + orderFlags.isPlaceBorder());
//
//    }
//
//        if (cancelScond) {
//        //debugPrint(candle, "Cancel sell order.");
////            logger.info("cancelScond");
////            //orderFlags.setCancelScond();
////            logger.info("" + orderFlags.isCancelScond());
//
//    }
//
//        if (crossLower) {
//        //debugPrint(candle, "Go short @ " + sPrice);
////            logger.info("crossLower");
////            orderFlags.setPlaceSorder();
////            logger.info("" + orderFlags.isPlaceSorder());
//
//    }
}
