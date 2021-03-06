/*
 * Copyright 2016 Davide Imbriaco <davide.imbriaco@gmail.com>.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.syncthing.java.core.utils;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ComparisonChain;
import net.syncthing.java.core.beans.FileInfo;

import java.util.Comparator;

public final class FileInfoOrdering {

    private final static Function<FileInfo, Boolean> isParentFunction = Functions.forPredicate(fileInfo -> PathUtils.isParent(fileInfo.getPath()));

    public static final Comparator<FileInfo> ALPHA_ASC_DIR_FIRST = (a, b) -> ComparisonChain.start()
        .compareTrueFirst(isParentFunction.apply(a), isParentFunction.apply(b))
        .compare(a.isDirectory() ? 1 : 2, b.isDirectory() ? 1 : 2)
        .compare(a.getPath(), b.getPath())
        .result();

    public static final Comparator<FileInfo> LAST_MOD_DESC = (a, b) -> ComparisonChain.start()
        .compareTrueFirst(isParentFunction.apply(a), isParentFunction.apply(b))
        .compare(b.getLastModified(), a.getLastModified())
        .compare(a.getPath(), b.getPath())
        .result();

}
