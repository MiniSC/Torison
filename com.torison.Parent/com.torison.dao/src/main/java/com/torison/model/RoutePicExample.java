package com.torison.model;

import java.util.ArrayList;
import java.util.List;

public class RoutePicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoutePicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRouteidIsNull() {
            addCriterion("routeID is null");
            return (Criteria) this;
        }

        public Criteria andRouteidIsNotNull() {
            addCriterion("routeID is not null");
            return (Criteria) this;
        }

        public Criteria andRouteidEqualTo(Integer value) {
            addCriterion("routeID =", value, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidNotEqualTo(Integer value) {
            addCriterion("routeID <>", value, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidGreaterThan(Integer value) {
            addCriterion("routeID >", value, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidGreaterThanOrEqualTo(Integer value) {
            addCriterion("routeID >=", value, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidLessThan(Integer value) {
            addCriterion("routeID <", value, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidLessThanOrEqualTo(Integer value) {
            addCriterion("routeID <=", value, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidIn(List<Integer> values) {
            addCriterion("routeID in", values, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidNotIn(List<Integer> values) {
            addCriterion("routeID not in", values, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidBetween(Integer value1, Integer value2) {
            addCriterion("routeID between", value1, value2, "routeid");
            return (Criteria) this;
        }

        public Criteria andRouteidNotBetween(Integer value1, Integer value2) {
            addCriterion("routeID not between", value1, value2, "routeid");
            return (Criteria) this;
        }

        public Criteria andRoutepic1IsNull() {
            addCriterion("routePic1 is null");
            return (Criteria) this;
        }

        public Criteria andRoutepic1IsNotNull() {
            addCriterion("routePic1 is not null");
            return (Criteria) this;
        }

        public Criteria andRoutepic1EqualTo(String value) {
            addCriterion("routePic1 =", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1NotEqualTo(String value) {
            addCriterion("routePic1 <>", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1GreaterThan(String value) {
            addCriterion("routePic1 >", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1GreaterThanOrEqualTo(String value) {
            addCriterion("routePic1 >=", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1LessThan(String value) {
            addCriterion("routePic1 <", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1LessThanOrEqualTo(String value) {
            addCriterion("routePic1 <=", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1Like(String value) {
            addCriterion("routePic1 like", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1NotLike(String value) {
            addCriterion("routePic1 not like", value, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1In(List<String> values) {
            addCriterion("routePic1 in", values, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1NotIn(List<String> values) {
            addCriterion("routePic1 not in", values, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1Between(String value1, String value2) {
            addCriterion("routePic1 between", value1, value2, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic1NotBetween(String value1, String value2) {
            addCriterion("routePic1 not between", value1, value2, "routepic1");
            return (Criteria) this;
        }

        public Criteria andRoutepic2IsNull() {
            addCriterion("routePic2 is null");
            return (Criteria) this;
        }

        public Criteria andRoutepic2IsNotNull() {
            addCriterion("routePic2 is not null");
            return (Criteria) this;
        }

        public Criteria andRoutepic2EqualTo(String value) {
            addCriterion("routePic2 =", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2NotEqualTo(String value) {
            addCriterion("routePic2 <>", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2GreaterThan(String value) {
            addCriterion("routePic2 >", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2GreaterThanOrEqualTo(String value) {
            addCriterion("routePic2 >=", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2LessThan(String value) {
            addCriterion("routePic2 <", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2LessThanOrEqualTo(String value) {
            addCriterion("routePic2 <=", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2Like(String value) {
            addCriterion("routePic2 like", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2NotLike(String value) {
            addCriterion("routePic2 not like", value, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2In(List<String> values) {
            addCriterion("routePic2 in", values, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2NotIn(List<String> values) {
            addCriterion("routePic2 not in", values, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2Between(String value1, String value2) {
            addCriterion("routePic2 between", value1, value2, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic2NotBetween(String value1, String value2) {
            addCriterion("routePic2 not between", value1, value2, "routepic2");
            return (Criteria) this;
        }

        public Criteria andRoutepic3IsNull() {
            addCriterion("routePic3 is null");
            return (Criteria) this;
        }

        public Criteria andRoutepic3IsNotNull() {
            addCriterion("routePic3 is not null");
            return (Criteria) this;
        }

        public Criteria andRoutepic3EqualTo(String value) {
            addCriterion("routePic3 =", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3NotEqualTo(String value) {
            addCriterion("routePic3 <>", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3GreaterThan(String value) {
            addCriterion("routePic3 >", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3GreaterThanOrEqualTo(String value) {
            addCriterion("routePic3 >=", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3LessThan(String value) {
            addCriterion("routePic3 <", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3LessThanOrEqualTo(String value) {
            addCriterion("routePic3 <=", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3Like(String value) {
            addCriterion("routePic3 like", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3NotLike(String value) {
            addCriterion("routePic3 not like", value, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3In(List<String> values) {
            addCriterion("routePic3 in", values, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3NotIn(List<String> values) {
            addCriterion("routePic3 not in", values, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3Between(String value1, String value2) {
            addCriterion("routePic3 between", value1, value2, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic3NotBetween(String value1, String value2) {
            addCriterion("routePic3 not between", value1, value2, "routepic3");
            return (Criteria) this;
        }

        public Criteria andRoutepic4IsNull() {
            addCriterion("routePic4 is null");
            return (Criteria) this;
        }

        public Criteria andRoutepic4IsNotNull() {
            addCriterion("routePic4 is not null");
            return (Criteria) this;
        }

        public Criteria andRoutepic4EqualTo(String value) {
            addCriterion("routePic4 =", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4NotEqualTo(String value) {
            addCriterion("routePic4 <>", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4GreaterThan(String value) {
            addCriterion("routePic4 >", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4GreaterThanOrEqualTo(String value) {
            addCriterion("routePic4 >=", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4LessThan(String value) {
            addCriterion("routePic4 <", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4LessThanOrEqualTo(String value) {
            addCriterion("routePic4 <=", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4Like(String value) {
            addCriterion("routePic4 like", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4NotLike(String value) {
            addCriterion("routePic4 not like", value, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4In(List<String> values) {
            addCriterion("routePic4 in", values, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4NotIn(List<String> values) {
            addCriterion("routePic4 not in", values, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4Between(String value1, String value2) {
            addCriterion("routePic4 between", value1, value2, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic4NotBetween(String value1, String value2) {
            addCriterion("routePic4 not between", value1, value2, "routepic4");
            return (Criteria) this;
        }

        public Criteria andRoutepic5IsNull() {
            addCriterion("routePic5 is null");
            return (Criteria) this;
        }

        public Criteria andRoutepic5IsNotNull() {
            addCriterion("routePic5 is not null");
            return (Criteria) this;
        }

        public Criteria andRoutepic5EqualTo(String value) {
            addCriterion("routePic5 =", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5NotEqualTo(String value) {
            addCriterion("routePic5 <>", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5GreaterThan(String value) {
            addCriterion("routePic5 >", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5GreaterThanOrEqualTo(String value) {
            addCriterion("routePic5 >=", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5LessThan(String value) {
            addCriterion("routePic5 <", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5LessThanOrEqualTo(String value) {
            addCriterion("routePic5 <=", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5Like(String value) {
            addCriterion("routePic5 like", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5NotLike(String value) {
            addCriterion("routePic5 not like", value, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5In(List<String> values) {
            addCriterion("routePic5 in", values, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5NotIn(List<String> values) {
            addCriterion("routePic5 not in", values, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5Between(String value1, String value2) {
            addCriterion("routePic5 between", value1, value2, "routepic5");
            return (Criteria) this;
        }

        public Criteria andRoutepic5NotBetween(String value1, String value2) {
            addCriterion("routePic5 not between", value1, value2, "routepic5");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}