
    create table `kpi`.`tkpihistorycompareformula`(
        `fsFormulaId` CHAR(36) not null,
       `fsKpiId` CHAR(36),
       `fsFormula` VARCHAR(200) not null comment '格式为：($-$)/$',
       `fsKpiYears` VARCHAR(500) comment '对应指标公式。比如公式为 ($+$)/$,则该字段应为本年,上一年,本年。每个$对应一个指标的年份',
       `fsTips` VARCHAR(200) comment '该提示信息中的$符合会被指标值给替换。
            比如：经济增加值（EVA）同比增长$以上
            替换后：经济增加值（EVA）同比增长  20% 以上
            
            ',
       `fsSymbol` VARCHAR(5) comment '有这6种类型：> 、 >=  、=   、<   、<=、!=',
       `fsThreshod` VARCHAR(10) comment '用来比对公式算出的值，如果不符合则提示',
       `fiPointDigit` INT,
       `fcExceptionPass` CHAR(1) comment '如果指标值不符合公式值是否进行下一步还是只弹出提示
            通过:Y
            不通过：N',
        primary key (`fsFormulaId`)
    );

    alter table `kpi`.`tkpihistorycompareformula`  
        add index `FK_Reference_4`(`fsKpiId`), 
        add constraint `FK_Reference_4` 
        foreign key (`fsKpiId`) 
        references `kpi`.`tkpiinfo`(`fsKpiId`);
    create unique index `PRIMARY` on `kpi`.`tkpihistorycompareformula`(`fsFormulaId`);
    create index `FK_Reference_4` on `kpi`.`tkpihistorycompareformula`(`fsKpiId`);