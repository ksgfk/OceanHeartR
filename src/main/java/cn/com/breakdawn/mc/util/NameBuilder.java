/*
  本源代码从ChinaCraft2仓库复制,开源地址:https://github.com/UnknownStudio/ChinaCraft2
  ChinaCraft2 Copyright (C) 2017 Unknown Domain
  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
  This is free software, and you are welcome to redistribute it
  nder certain conditions; type `show c' for details.

  The hypothetical commands `show w' and `show c' should show the appropriate
  parts of the General Public License.  Of course, your program's commands
  might be different; for a GUI interface, you would use an "about box".

  You should also get your employer (if you work as a programmer) or school,
  if any, to sign a "copyright disclaimer" for the program, if necessary.
  For more information on this, and how to apply and follow the GNU GPL, see
  <https://www.gnu.org/licenses/>.

  The GNU General Public License does not permit incorporating your program
  into proprietary programs.  If your program is a subroutine library, you
  may consider it more useful to permit linking proprietary applications with
  the library.  If this is what you want to do, use the GNU Lesser General
  Public License instead of this License.  But first, please read
  <https://www.gnu.org/licenses/why-not-lgpl.html>.
 */
package cn.com.breakdawn.mc.util;

import javax.annotation.Nonnull;
import java.util.Arrays;

public interface NameBuilder {
    @Nonnull
    static String buildRegistryName(String... params) {
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append('_');
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    @Nonnull
    static String buildUnlocalizedName(String... params) {
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append(Character.toUpperCase(s.charAt(0)));
            stringBuilder.append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
