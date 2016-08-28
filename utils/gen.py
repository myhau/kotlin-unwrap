#! /usr/bin/env python3

import os

template = '''
inline fun <{0}, R> unwrap(
{1},
    block: ({0}) -> R): R? {{
  return if ({2}) block({3}) else null
}}

inline fun <{0}, R> notnull(
{1},
    block: () -> R): R? {{
  return if ({2}) block() else null
}}
'''

if __name__ == '__main__':
    max = 12
    root = os.path.join('src', 'main', 'kotlin', '')
    path = [i[0] for i in os.walk(root)
            if i[0].endswith(os.sep + 'unwrap')][0].replace(root, '')
    codes = ["package {0}\n".format(path.replace("/", "."))]

    for iter in range(1, max + 1):
        types = ', '.join(['T{}'.format(i + 1) for i in range(iter)])
        params = ',\n'.join(['{1}t{0}: T{0}?'.format(i + 1, ' ' * 8)
                             for i in range(iter)])
        conditions = '\n{}&& '.format(' ' * 16) \
            .join(['t{} != null'.format(i + 1) for i in range(iter)])
        args = ', '.join(['t{}'.format(i + 1) for i in range(iter)])
        code = template.format(types, params, conditions, args)
        codes.append(code)

    filename = os.path.join(root, path, 'Unwrap.kt')
    with open(filename, 'w') as fout:
        fout.write(''.join(codes).strip() + '\n')
    pass
