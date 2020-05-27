package com.server.service;


/**
 * @Description:
 * @Version:
 */
public interface IMiningService {
    /**
     * @param dto
     * @return
     */
    MiningIndexVo indexData(MiningDto dto);

    /**
     * @param dto
     * @return
     */
    boolean ReceivePo(MiningDto dto);

    /**
     * @return
     */
    ComConfigAgreement activiteRule();

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    PageInfo<StockUserMiningInfo> miningInfo(MiningDto dto, PageVo pageVo);

    /**
     * @param dto
     * @param pageVo
     * @return
     */
    PageInfo<StockUserForceInfo> forceInfo(MiningDto dto, PageVo pageVo);

    /**
     * @param dto
     * @return
     */
    Object paoList(MiningDto dto);
}
