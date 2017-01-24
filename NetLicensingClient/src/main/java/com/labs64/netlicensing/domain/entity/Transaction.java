/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.labs64.netlicensing.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.labs64.netlicensing.domain.vo.Currency;
import com.labs64.netlicensing.domain.vo.PriceType;
import com.labs64.netlicensing.domain.vo.TransactionSource;
import com.labs64.netlicensing.domain.vo.TransactionStatus;

/**
 * Transaction entity used internally by NetLicensing.
 * <p/>
 * Properties visible via NetLicensing API:
 * <p/>
 * <b>number</b> - Unique number (across all products of a vendor) that identifies the transaction. This number is
 * always generated by NetLicensing.
 * <p/>
 * <b>active</b> - always true for transactions
 * <p/>
 * <b>status</b> - see {@link TransactionStatus}
 * <p/>
 * <b>source</b> - see {@link TransactionSource}
 * <p/>
 * <b>price</b> - price for the transaction.
 * <p/>
 * <b>discount</b> - discount for the transaction.
 * <p/>
 * <b>currency</b> - specifies currency for the transaction price. Check data types to discover which currencies are
 * supported.
 */
public interface Transaction extends BaseEntity {

    // Methods for working with properties

    TransactionStatus getStatus();

    void setStatus(TransactionStatus status);

    TransactionSource getSource();

    void setSource(TransactionSource source);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    BigDecimal getDiscount();

    void setDiscount(BigDecimal discount);

    Currency getCurrency();

    void setCurrency(Currency currency);

    BigDecimal getVat();

    void setVat(BigDecimal vat);

    Long getCountryId();

    void setCountryId(Long countryId);

    PriceType getPriceType();

    void setPriceType(PriceType priceType);

    Date getDateCreated();

    void setDateCreated(Date dateCreated);

    Date getDateClosed();

    void setDateClosed(Date dateClosed);

    // Methods for working with custom properties

    @Deprecated
    Map<String, String> getTransactionProperties();

}
