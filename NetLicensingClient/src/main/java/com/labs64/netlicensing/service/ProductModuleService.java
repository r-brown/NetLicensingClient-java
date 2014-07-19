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
package com.labs64.netlicensing.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import org.apache.commons.lang3.StringUtils;

import com.labs64.netlicensing.domain.Constants;
import com.labs64.netlicensing.domain.entity.ProductModule;
import com.labs64.netlicensing.domain.vo.Context;
import com.labs64.netlicensing.domain.vo.Page;
import com.labs64.netlicensing.exception.BaseCheckedException;
import com.labs64.netlicensing.util.CheckUtils;

/**
 * Provides product module handling routines.
 * <p/>
 * {@linkplain ProductService Product} may comprise of multiple product modules, but must have at least one. Each
 * product module is licensed using one of the licensing models offered by NetLicensing service. Licensing within a
 * product module is independent of other product modules, however all product modules of a single product are visible
 * to every {@linkplain LicenseeService licensee} of the product.
 */
public class ProductModuleService {

    static final String CONTEXT_PATH = "productmodule";

    /**
     * Creates new product module object with given properties.
     *
     * @param context
     *            determines the vendor on whose behalf the call is performed
     * @param productNumber
     *            parent product to which the new product module is to be added
     * @param productModule
     *            non-null properties will be taken for the new object, null properties will either stay null, or will
     *            be set to a default value, depending on property.
     * @return the newly created product module object
     * @throws BaseCheckedException
     *             any subclass of {@linkplain BaseCheckedException}. These exceptions will be transformed to the
     *             corresponding service response messages.
     */
    public static ProductModule create(final Context context, final String productNumber, final ProductModule productModule) throws BaseCheckedException {
        CheckUtils.paramNotNull(productModule, "productNumber");

        final Form form = productModule.asRequestForm();
        if (!StringUtils.isEmpty(productNumber)) {
            form.param(Constants.Product.PRODUCT_NUMBER, productNumber);
        }
        return NetLicensingService.getInstance().post(context, CONTEXT_PATH, form, ProductModule.class);
    }

    /**
     * Gets product module by its number.
     *
     * @param context
     *            determines the vendor on whose behalf the call is performed
     * @param number
     *            the product module number
     * @return the product module
     * @throws BaseCheckedException
     *             any subclass of {@linkplain BaseCheckedException}. These exceptions will be transformed to the
     *             corresponding service response messages.
     */
    public static ProductModule get(final Context context, final String number) throws BaseCheckedException {
        CheckUtils.paramNotEmpty(number, "number");

        return NetLicensingService.getInstance().get(context, CONTEXT_PATH + "/" + number, null, ProductModule.class);
    }

    /**
     * Returns all product modules of a vendor.
     *
     * @param context
     *            determines the vendor on whose behalf the call is performed
     * @return list of product modules (of all products) or null/empty list if nothing found.
     * @throws BaseCheckedException
     *             any subclass of {@linkplain BaseCheckedException}. These exceptions will be transformed to the
     *             corresponding service response messages.
     */
    public static Page<ProductModule> list(final Context context) throws BaseCheckedException {
        return NetLicensingService.getInstance().list(context, CONTEXT_PATH, ProductModule.class);
    }

    /**
     * Updates product module properties.
     *
     * @param context
     *            determines the vendor on whose behalf the call is performed
     * @param number
     *            product module number
     * @param productModule
     *            non-null properties will be updated to the provided values, null properties will stay unchanged.
     * @return updated product module.
     * @throws BaseCheckedException
     *             any subclass of {@linkplain BaseCheckedException}. These exceptions will be transformed to the
     *             corresponding service response messages.
     */
    public static ProductModule update(final Context context, final String number, final ProductModule productModule) throws BaseCheckedException {
        CheckUtils.paramNotEmpty(number, "number");
        CheckUtils.paramNotNull(productModule, "productModule");

        return NetLicensingService.getInstance().post(context, CONTEXT_PATH + "/" + number, productModule.asRequestForm(), ProductModule.class);
    }

    /**
     * Deletes product module.
     *
     * @param context
     *            determines the vendor on whose behalf the call is performed
     * @param number
     *            product module number
     * @param forceCascade
     *            if true, any entities that depend on the one being deleted will be deleted too
     * @throws BaseCheckedException
     *             any subclass of {@linkplain BaseCheckedException}. These exceptions will be transformed to the
     *             corresponding service response messages.
     */
    public static void delete(final Context context, final String number, final boolean forceCascade) throws BaseCheckedException {
        CheckUtils.paramNotEmpty(number, "number");

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put(Constants.CASCADE, forceCascade);
        NetLicensingService.getInstance().delete(context, CONTEXT_PATH + "/" + number, params);
    }

}
